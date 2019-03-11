package algorithm;

/**
 * 算法接口
 * @author 李俊宏
 */
public interface Algorithm {

    /**
     * @param prefixExp 可能含有空白字符的中缀表达式字符串
     * @throws Exception 转换时的异常
     * @return  转换后的后缀表达式
     */
    public String translateToSuffixExp(String prefixExp) throws Exception;
}
