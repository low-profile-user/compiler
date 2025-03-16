package Render.Expressions;

import Render.BaseTypedElement;
import Render.FlowControllers.ConditionalElement;

public class LogicalOperator extends AbstractExpression {

  private String operatorBytecode;
  private String targetLabel;
  private String operator;
  public LogicalOperator(String operator, BaseTypedElement a, BaseTypedElement b, String targetLabel) {
    if (a.getType().equals("string") && a.getType().equals(b.getType())) {
      this.setType("string");
    } else if(a.getTypeIndex() < 4 && b.getTypeIndex() < 4) {
      a.setConversionIndex(3);
      b.setConversionIndex(3);
      this.setType(MathOperator.typeArray[3]);
    } else {
      System.out.println("[ERROR] Não é possível comparar string com valor do tipo númerico.");
    }
    this.setOperator(operator);
    this.targetLabel = targetLabel;
  }

  public static String getInvertedOperator(String operator) {
    switch (operator) {
      case "==":
        return "!=";
      case ">":
        return "<=";
      case "<":
        return ">=";
      case "<=":
        return ">";
      case "!=":
        return "==";
      default:
        System.out.println("[ ERROR ] Operador inválido.\r\n" + "Tipo desconhecido: "+ operator + "\r\n");
        System.exit(1);
        return "";
    }
  }

  public void invertOperator(String operator) {
    this.setOperator(LogicalOperator.getInvertedOperator(operator));
  }

  public void setOperator(String operator) {
    this.operator = operator;
    switch (operator) {
      case "==":
        this.operatorBytecode = "ifeq";
        break;
      case ">":
        this.operatorBytecode = "ifgt";
        break;
        case ">=":
        this.operatorBytecode = "ifge";
        break;
      case "<":
        this.operatorBytecode = "iflt";
        break;
      case "<=":
        this.operatorBytecode = "ifle"; 
        break;
      case "!=":
        this.operatorBytecode = "ifne";
        break;
      default:
        System.out.println("[ ERROR ] Operador inválido.\r\n" + "Tipo desconhecido: "+ operator + "\r\n");
        System.exit(1);
        break;
    }
  }

  public String render() {
    String finalRender = "";
    if (this.type.equals("string")) {
      finalRender += "invokevirtual java/lang/String/compareTo("+this.getTypeArrayByteCodeKey()+")I\r\n";
    } else {
      finalRender += "dcmpl\r\n";
    }
    finalRender += this.operatorBytecode + " " + this.targetLabel + "\r\n";
    return finalRender;
  }
}
