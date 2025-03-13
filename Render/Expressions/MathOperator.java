package Render.Expressions;

import Render.BaseTypedElement;

public class MathOperator extends AbstractExpression {
  private String operatorByteCode;
  public MathOperator(String operator, BaseTypedElement a, BaseTypedElement b) {
    int maxTypeIndex = Math.max(a.getTypeIndex(), b.getTypeIndex());
    a.setConversionIndex(maxTypeIndex);
    b.setConversionIndex(maxTypeIndex);
    this.setType(MathOperator.typeArray[maxTypeIndex]);
    this.setOperator(operator);
  }

  public void setOperator(String operator) {
    switch (operator) {
      case "+":
        this.operatorByteCode = "add";
        break;
      case "-":
        this.operatorByteCode = "sub";
        break;
      case "*":
        this.operatorByteCode = "mul";
        break;
      case "/":
        this.operatorByteCode = "div";
        break;
      default:
        System.out.println("[ ERROR ] Operador inválido.\r\n" + "Tipo desconhecido: "+ operator + "\r\n");
        System.exit(1);
        break;
    }
  }

  public String render() {
    return this.typePrefix + this.operatorByteCode + "\r\n" + this.getConversionByteCode();
  }
}
