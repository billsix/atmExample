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
package com.billsix.examples.spring.introductions;

import com.billsix.examples.spring.dynamicproxies.Authenticatable;
import java.lang.reflect.Method;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AuthenticatableIntroductionInterceptor extends DelegatingIntroductionInterceptor implements Authenticatable{
    
    public void authenticate(String username, String password) {
        _isAuthenticated = true;
    }
    public boolean isValidUser() {
        return _isAuthenticated;
    }
    
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        if(!isValidUser() && !method.equals(Authenticatable.class.getMethod("authenticate", String.class, String.class))) {
            throw new IllegalStateException("User is not authenticated");
        }
        return super.invoke(invocation);
    }
    
    private boolean _isAuthenticated = false;
}
