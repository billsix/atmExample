'From Squeak3.9 of 7 November 2006 [latest update: #7067] on 2 August 2007 at 11:48:24 pm'!Object subclass: #CalculatorService	instanceVariableNames: ''	classVariableNames: ''	poolDictionaries: ''	category: 'CalculatorService'!ProtoObject subclass: #CustomProxyClass	instanceVariableNames: 'target'	classVariableNames: ''	poolDictionaries: ''	category: 'CalculatorService'!!CustomProxyClass commentStamp: 'wes 8/2/2007 23:40' prior: 0!asdf!ClassTestCase subclass: #CustomProxyClassTest	instanceVariableNames: 'calculatorService'	classVariableNames: ''	poolDictionaries: ''	category: 'CalculatorService'!!CustomProxyClassTest commentStamp: 'wes 8/2/2007 23:41' prior: 0!asdf!!CalculatorService methodsFor: 'as yet unclassified' stamp: 'wes 7/9/2007 20:07'!add: first and: second	^ first + second .		! !!CustomProxyClass methodsFor: 'initialization' stamp: 'wes 8/2/2007 23:43'!doesNotUnderstand: aMessage 	| toReturn |	Transcript show: 'Here I would open a transaction';		        cr.	toReturn := target perform: aMessage selector withArguments: aMessage arguments.	Transcript show: 'Here commit the transaction unless I caught an unrecoverable exception!!';		        cr.	^ toReturn .	!]style[(19 8 17 10 7 1 31 1 29 6 10 8 25 8 13 10 7 1 71 1 33)f2b,f2cblue;b,f2,f2cmagenta;,f2,f2c255146000,f2,f2c255146000,f2,f2cmagenta;,f2,f2cblue;i,f2,f2cblue;i,f2,f2cmagenta;,f2,f2c255146000,f2,f2c255146000,f2! !!CustomProxyClass methodsFor: 'initialization' stamp: 'wes 7/8/2007 21:46'!initializeTarget: t 	target := t!]style[(18 1 3 6 4 1)f2b,f2cblue;b,f2,f2cmagenta;,f2,f2cblue;i! !!CustomProxyClass class methodsFor: 'as yet unclassified' stamp: 'wes 7/8/2007 21:56'!target: t 	^ self basicNew initializeTarget: t!]style[(8 1 5 4 28 1)f2b,f2cblue;b,f2,f2cmagenta;,f2,f2cblue;i! !!CustomProxyClassTest methodsFor: 'tests' stamp: 'wes 8/2/2007 23:41'!setUp	calculatorService := CustomProxyClass target: (CalculatorService new).! !!CustomProxyClassTest methodsFor: 'tests' stamp: 'wes 8/2/2007 23:48'!testCalculatorService	self assert: ( (calculatorService add: 1 and: 2) = 3)			!]style[(21 2 4 12 17 6 1 6 6 5)f2b,f2,f2cmagenta;,f2,f2cmagenta;,f2,f2c255146000,f2,f2c255146000,f2! !CustomProxyClassTest removeSelector: #testValidAccess!!CustomProxyClassTest reorganize!('tests' setUp testCalculatorService)!