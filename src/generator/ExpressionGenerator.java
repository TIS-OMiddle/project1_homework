package generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ExpressionGenerator {
	private static Random random = new Random();
	public static final Map<Integer, String> map = new HashMap<Integer, String>();
	static {
		map.put(0, "/");
		map.put(1, "+");
		map.put(2, "-");
		map.put(3, "*");
		map.put(4, "(");
		map.put(5, ")");
	}
	
	/**
	 * 获取一个随机生成的产生式，传入的参数表达式是否为正确的表达式
	 * 
	 * @param right  
	 * 		true: 返回一个正确的中缀表达式
	 * 		false: 返回一个错误的表达式
	 * @return
	 * 		返回一个中缀表达式（错误或者正确）
	 */
	public static String getInfixExpression(boolean right) {
		int length = random.nextInt(15); // 15是类型个数不是String长度
		length += 5; // minimum length
		if( length > 15 )
			length = 16;
		if( right ) 
			return getCorrectInfixExpression(length);
		
		return getRandomString(length);
	}
	
//	@deprecated
//	 * @param bound
//	 * 		随机数范围为 0~bound-1  之间
//	 * @param _minus
//	 * 		true:	负数
//	 * 		false:	正数
//	 * @param _float
//	 * 		true: 	返回的为一个浮点数
//	 * 		false: 	返回的是一个整形数
//	 * @return
//	 * 		返回随机数
//	 */
//	private static String getRandomNumber(int bound, boolean _minus, boolean _float) {
//		
//		
//		
//		return null;
//	}
	
	/**
	 * 随机生成运算符号
	 * 
	 * @return
	 */
	private static String getRandomOperator() {
		int rdm = random.nextInt(4);
		return map.get(rdm);
		
	}
	
	
	/**
	 * 随机生成一个绝对值在特定范围的数	
	 * 	-bound < x < bound
	 * 
	 * @param bound 
	 * 		数的范围
	 * @return
	 * 		返回字符串
	 */	
	private static String getRandomNumber(int bound) {
		int _minus, _float, iTemp;
		float fTemp;
		StringBuilder sBuilder = new StringBuilder() ;
		_minus = random.nextInt(4);
		_float = random.nextInt(2);
		
		if( _float == 1 ) {
			fTemp = random.nextFloat()*bound;
			// FIXME: precision
			sBuilder.append(String.format("%.1f", fTemp));
		} else {
			iTemp = random.nextInt(bound);
			sBuilder.append(iTemp+"");
		}
		
		if( _minus == 1 ) {
			sBuilder.insert(0, "(-");
			sBuilder.append(")");
		} 
		
		return sBuilder.toString();
	}	 
	
	private static String getRandomString(int length) {
		StringBuilder sBuilder = new StringBuilder();
		int rand;
		boolean isNum = false; // 保证不会连续出现两次数字
		while( length-- != 0 ) {
			rand = random.nextInt(2);
			if( rand == 0 ) {
				if( !isNum) {
					sBuilder.append(getRandomNumber(100));
					isNum = true;
					continue;
				}
			} 
			rand = random.nextInt(map.size());
			sBuilder.append(map.get(rand));
			isNum = false;
		}
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @param depth
	 * 		递归的深度，防止表达式过长
	 * @return
	 * 		返回一个子表达式
	 */
	private static String generateChildExpresion( int depth) {
		String child = null;
		StringBuilder s = new StringBuilder();
		int director;
		if( depth > 2 ) {
			return getRandomNumber(99);
		}
		director = random.nextInt(2);
		/**
		 * 这里生成一个开始符号，所有表达式的开始符号一定是 '('
		 * 或者是一个数字
		 * 0 - (ChildExpression)    
		 * 1 - randomNumber
		 */
		switch (director) {
			case 0:
				child = generateChildExpresion(depth+1);
				director = random.nextInt(3);
				if( director == 2 ) {
					s.append("(");
					s.append(child);
					s.append(")");
					// 子表达式随机加一个括号，改变优先级
				} else {
					s.append(child);
				}
				break;
			case 1:
				child = getRandomNumber(100);
				s.append(child);
				break;
			default:
				// FIXME: EXCEPTION
				break;
		}

		director = random.nextInt(2);
		// 		 * 0 - operator(+ - * /)  
		//		 * 1 - return 
		switch (director) {
		case 0:
			s.append(getRandomOperator());
			s.append(generateChildExpresion( depth+1));
			break;
		default:
			break;
		}
		return s.toString();
	}
	
	/**
	 * 生成一个正确的中序表达式
	 * 
	 * @param length
	 * 		表达式的长度
	 * @return
	 * 		表达式
	 */
	private static String getCorrectInfixExpression(int length) {
		/**
		 * 	S -> E
		 *	E -> (E) 	
		 * 	E -> +E, -E, /E, *E
		 * 	E -> num{ (-100,100) float/int} 	
		 */
		int depth = 0;
		StringBuilder stringBuilder = new StringBuilder();
		int maxLength = length * 3;
		String exp = generateChildExpresion( depth);
		
		if( exp.length() > maxLength ) {
			return exp;
		}
		stringBuilder.append(exp);
		stringBuilder.append(getRandomOperator());
		if( exp.length() > length) 
			stringBuilder.append(generateChildExpresion(1));
		else // 这里说明子表达式还很短，所以可以给递归层数加深
			stringBuilder.append(generateChildExpresion(0));
		return stringBuilder.toString();
	}
	
}
