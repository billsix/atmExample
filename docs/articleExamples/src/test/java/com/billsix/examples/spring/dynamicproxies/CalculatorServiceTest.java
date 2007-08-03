/*
 Copyright (c) 2007 Bill Six
 
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
 
The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.
 
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */
package com.billsix.examples.spring;
import com.billsix.examples.spring.dynamicproxies.TransactionInterceptor;
import com.billsix.examples.spring.dynamicproxies.CalculatorService;
import com.billsix.examples.spring.dynamicproxies.CalculatorServiceImplementation;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.aop.framework.ProxyFactoryBean;

public class CalculatorServiceTest extends TestCase {
    public CalculatorServiceTest( String testName ) {
        super( testName );
    }
    
    public static Test suite() {
        return new TestSuite( CalculatorServiceTest.class );
    }
    
    public void setUp() throws Exception{
        CalculatorServiceImplementation target = new CalculatorServiceImplementation();
        ProxyFactoryBean factory = new ProxyFactoryBean();
        factory.addAdvice(new TransactionInterceptor());
        factory.setTarget(target);
        factory.setProxyInterfaces(new Class[]{CalculatorService.class});
        calculatorService = (CalculatorService) factory.getObject();
    }
    
    public void testValidAccess() {
        assertTrue(calculatorService.add(4,5) == 9);
    }
    
    CalculatorService calculatorService ;
}
