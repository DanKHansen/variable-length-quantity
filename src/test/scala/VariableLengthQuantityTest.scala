import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

/** @version 1.1.0 */
class VariableLengthQuantityTest extends AnyFunSuite with Matchers:

   test("zero") {
      VariableLengthQuantity.encode(List(0x0)) should be(List(0x0))
   }

   test("arbitrary single byte") {
      // pending
      VariableLengthQuantity.encode(List(0x40)) should be(List(0x40))
   }

   test("largest single byte") {
      // pending
      VariableLengthQuantity.encode(List(0x7f)) should be(List(0x7f))
   }

   test("smallest double byte") {
      // pending
      VariableLengthQuantity.encode(List(0x80)) should be(List(0x81, 0x0))
   }

   test("arbitrary double byte") {
      // pending
      VariableLengthQuantity.encode(List(0x2000)) should be(List(0xc0, 0x0))
   }

   test("largest double byte") {
      // pending
      VariableLengthQuantity.encode(List(0x3fff)) should be(List(0xff, 0x7f))
   }

   test("smallest triple byte") {
      // pending
      VariableLengthQuantity.encode(List(0x4000)) should be(List(0x81, 0x80, 0x0))
   }

   test("arbitrary triple byte") {
      // pending
      VariableLengthQuantity.encode(List(0x100000)) should be(List(0xc0, 0x80, 0x0))
   }

   test("largest triple byte") {
      // pending
      VariableLengthQuantity.encode(List(0x1fffff)) should be(List(0xff, 0xff, 0x7f))
   }

   test("smallest quadruple byte") {
      // pending
      VariableLengthQuantity.encode(List(0x200000)) should be(List(0x81, 0x80, 0x80, 0x0))
   }

   test("arbitrary quadruple byte") {
      // pending
      VariableLengthQuantity.encode(List(0x8000000)) should be(List(0xc0, 0x80, 0x80, 0x0))
   }

   test("largest quadruple byte") {
      // pending
      VariableLengthQuantity.encode(List(0xfffffff)) should be(List(0xff, 0xff, 0xff, 0x7f))
   }

   test("smallest quintuple byte") {
      // pending
      VariableLengthQuantity.encode(List(0x10000000)) should be(List(0x81, 0x80, 0x80, 0x80, 0x0))
   }

   test("arbitrary quintuple byte") {
      // pending
      VariableLengthQuantity.encode(List(0xff000000)) should be(List(0x8f, 0xf8, 0x80, 0x80, 0x0))
   }

   test("maximum 32-bit integer input") {
      // pending
      VariableLengthQuantity.encode(List(0xffffffff)) should be(List(0x8f, 0xff, 0xff, 0xff, 0x7f))
   }

   test("two single-byte values") {
      // pending
      VariableLengthQuantity.encode(List(0x40, 0x7f)) should be(List(0x40, 0x7f))
   }

   test("two multi-byte values") {
      // pending
      VariableLengthQuantity.encode(List(0x4000, 0x123456)) should be(List(0x81, 0x80, 0x0, 0xc8, 0xe8, 0x56))
   }

   test("many multi-byte values") {
      // pending
      VariableLengthQuantity.encode(List(0x2000, 0x123456, 0xfffffff, 0x0, 0x3fff, 0x4000)) should be(
        List(0xc0, 0x0, 0xc8, 0xe8, 0x56, 0xff, 0xff, 0xff, 0x7f, 0x0, 0xff, 0x7f, 0x81, 0x80, 0x0))
   }

   test("one byte") {
      // pending
      VariableLengthQuantity.decode(List(0x7f)) should be(Right(List(0x7f)))
   }

   test("two bytes") {
      // pending
      VariableLengthQuantity.decode(List(0xc0, 0x0)) should be(Right(List(0x2000)))
   }

   test("three bytes") {
      // pending
      VariableLengthQuantity.decode(List(0xff, 0xff, 0x7f)) should be(Right(List(0x1fffff)))
   }

   test("four bytes") {
      // pending
      VariableLengthQuantity.decode(List(0x81, 0x80, 0x80, 0x0)) should be(Right(List(0x200000)))
   }

   test("maximum 32-bit integer") {
      // pending
      VariableLengthQuantity.decode(List(0x8f, 0xff, 0xff, 0xff, 0x7f)) should be(Right(List(0xffffffff)))
   }

   test("incomplete sequence causes error") {
      // pending
      VariableLengthQuantity.decode(List(0xff)).isLeft should be(true)
   }

   test("incomplete sequence causes error, even if value is zero") {
      // pending
      VariableLengthQuantity.decode(List(0x80)).isLeft should be(true)
   }

   test("multiple values") {
      // pending
      VariableLengthQuantity.decode(
        List(0xc0, 0x0, 0xc8, 0xe8, 0x56, 0xff, 0xff, 0xff, 0x7f, 0x0, 0xff, 0x7f, 0x81, 0x80, 0x0)) should be(
        Right(List(0x2000, 0x123456, 0xfffffff, 0x0, 0x3fff, 0x4000)))
   }
