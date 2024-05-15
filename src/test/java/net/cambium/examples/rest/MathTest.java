package net.cambium.examples.rest;

import static org.junit.jupiter.api.Assertions.*;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;
import org.junit.jupiter.api.Test;

class MathTest {

//  @Test
//  void testDouble() {
//    double x = 1.5707963267948966;
//    double a = 6.123233995736766E-17;
//    double b = 1.722526201536342E-16;
//    
//    assertFalse(a == 0.0d);
//    assertFalse(b == 0.0d);
//    assertFalse(a == b);
//    //Arccos() not precise enough to invert: inputs are so small that they are equated to zero
//    assertTrue(Math.acos(a) == Math.acos(0.0d));
//    assertTrue(Math.acos(b) == Math.acos(0.0d));
//    
//    assertTrue(Math.cos(x) == a);
//    assertTrue(Math.cos(x) != b);
//  }
//  
  @Test
  void testApfloat() {
    double x0 = 1.5707963267948966;
    double a0 = 6.123233995736766E-17;
    double b0 = 1.722526201536342E-16;
    int precision = 33;
    Apfloat a = new Apfloat(a0, precision);
    Apfloat b = new Apfloat(b0, precision);
    Apfloat x = new Apfloat(x0, precision);
    Apfloat z = new Apfloat(0.0d, precision);
    
    assertFalse(a.equals(z));
    assertFalse(b.equals(z));
    assertFalse(a.equals(b));
    
//    System.out.println(ApfloatMath.acos(a));
//    System.out.println();    
//    System.out.println(ApfloatMath.acos(a).doubleValue());
//    System.out.println(ApfloatMath.acos(b).doubleValue());
//    System.out.println(ApfloatMath.cos(x));
//    
//       
    assertFalse(ApfloatMath.cos(x).equals(a));
    assertFalse(ApfloatMath.cos(x).equals(b));
    
    //This is the minimum required precision for cosine to give you the correct double answer:
                           //1.5707963267948966
    Apfloat c = new Apfloat("1.57079632679489644697870153800555", precision);
                             //01234567890123456789012345678901    
    System.out.println(ApfloatMath.cos(c));    
    System.out.println(ApfloatMath.cos(c).doubleValue());
    System.out.println(1.722526201536342E-16);
  }

//  @Test
//  public void testMath() {
//    double target    = 1.722526201536342E-16;
//    double threshold = 1.5707963267948966;
//    int precision = 0;
//    while(precision++ < 50) {
//      Apfloat t = new Apfloat(target, precision);
//      Apfloat acos = ApfloatMath.acos(t);
//      System.out.println("acos(" + t + ") = " + acos);
//      
//      double test = acos.doubleValue();
////      if(Double.compare(test, threshold) != 0) {
////        System.out.println(acos);
////        System.out.println("Precision " + precision + " does not reach threshold");
////        continue;
////      }
//      
////      Apfloat cos  = ApfloatMath.cos(acos);
////      double d = cos.doubleValue();
////      if(Double.compare(d, target) == 0) {
////        System.out.println("Precision match: " + precision + " --> " + d);
////        break;
////      }
////      //otherwise
////      System.out.println(d);
//    }
//  }
//        
//    double input  = 1.5707963267948966;
//    double output = 1.722526201536342E-16;
//    int precision = 0;
//    while(precision++ < 50) {
//      Apfloat in = new Apfloat(input, precision);
//      Apfloat cos  = ApfloatMath.cos(in);
//      double d = cos.doubleValue();
//      if(Double.compare(d, output) == 0) {
//        System.out.println("Precision match: " + precision + " --> " + d);
//        break;
//      }
//      //otherwise
//      System.out.println(d);
//    }    
//    
//  }
//  
}
