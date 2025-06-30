object VariableLengthQuantity:

   def encode(ns: List[Int]): List[Int] =
      def f(n: Int): List[Int] =
         n.toBinaryString.reverse
            .grouped(7)
            .toList
            .reverse
            .map(_.reverse)
            .map(s => "0" * (7 - s.length) + s)
            .reverse
            .zipWithIndex
            .map { case (s, idx) => if idx == 0 then "0" + s else "1" + s }
            .map(Integer.parseInt(_, 2))
            .reverse

      ns.flatMap(f)

   def decode(ns: List[Int]): Either[Exception, List[Int]] =
      def f(ns: List[Int]): Either[Exception, List[Int]] =
         Right {
            Integer.parseUnsignedInt(ns.map(_.toBinaryString.grouped(8).mkString).map(s => "0" * (8 - s.length) + s).map(_.drop(1)).mkString,2) :: Nil
         }


      val b = ns.map(_.toBinaryString.grouped(8).toList)
      (b.length, b.head) match
         case (1, ::(head, _)) if head.length > 7 && head(0) == '1' => Left(new Exception())
         case (1, ::(head, tail)) if head.length < 8                => Right((head :: tail).map(Integer.parseInt(_, 2)))
         case _                                                     => f(ns)
