object VariableLengthQuantity:

   def encode(ns: List[Int]): List[Int] =
      def doStuff(n: Int): List[Int] =
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

      ns.flatMap(doStuff)

   def decode(numbers: List[Int]): Either[Exception, List[Int]] = ???
