package Render.Expressions;

import java.util.LinkedList;

public class StringMathOperator extends AbstractExpression {
  private LinkedList<LinkedList<AbstractExpression>> children;
  public StringMathOperator(String operator, LinkedList<LinkedList<AbstractExpression>> elements ) {
    int stringTypeIndex = 4;
    this.children = elements; 
   
    this.setType(MathOperator.typeArray[stringTypeIndex]);
    this.setOperator(operator);
  }
  
  public StringMathOperator(String operator) {
    int stringTypeIndex = 4;
    this.children = new LinkedList<LinkedList<AbstractExpression>>(); 
   
    this.setType(MathOperator.typeArray[stringTypeIndex]);
    this.setOperator(operator);
  }

  public void setOperator(String var1) {
    switch (var1) {
       case "+":
          break;
       default:
          System.out.println("[ ERROR ] Operador inválido para concatenação de strings.\r\nTipo desconhecido: " + var1 + "\r\n");
          System.exit(1);
    }
  }

  public void addChild(LinkedList<AbstractExpression> child) {
    if(!child.isEmpty()) {
      this.children.add(child);
    }
  }
 

  public String renderChildren(boolean dryRun) {
    String childrenRender = "";

    for(LinkedList<AbstractExpression> childSet : this.children) {
      for (AbstractExpression child: childSet) {
        childrenRender += child.render();
      }
      if (!childSet.isEmpty() && !dryRun) childrenRender += "invokevirtual java/lang/StringBuilder/append("+ childSet.getLast().getTypeArrayByteCodeKey() +")Ljava/lang/StringBuilder;\r\n";
    }

    return childrenRender;
  }

  public String renderChildren() {
   return this.renderChildren(false);
  }

  public String render() {
    if (this.children.size() < 2) return this.renderChildren(true);

    String finalRender = "new java/lang/StringBuilder\r\ndup\r\n";
    finalRender += "invokespecial java/lang/StringBuilder/<init>()V\r\n";
    finalRender += this.renderChildren();
    finalRender += "invokevirtual java/lang/StringBuilder/toString()Ljava/lang/String;\r\n";

    return finalRender;
  }

}
