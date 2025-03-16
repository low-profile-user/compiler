import java.util.HashMap;

import Render.Commands.VariableAttribution;
import Render.Expressions.Variable;

public class ReferenceTable {
  private HashMap<String, VariableAttribution> tab;
  public ReferenceTable() {
        this.tab = new HashMap<String,VariableAttribution>();
  }
  public boolean addVariable(VariableAttribution variable) {
        if(this.tab.containsKey(variable.getReferenceName()))
              return false;
        else {
              this.tab.put(variable.getReferenceName(), variable);
              return true;
        }
  }
  public int getVariableSerialID(String referenceName) {
        return ((VariableAttribution)this.tab.get(referenceName)).getVariableSerialID();
  }

  public Variable stackVariable(String referenceName) {
      if(!this.exists(referenceName)){ 
            System.out.println("[ ERROR ] [ Semântico ] \n A variável "+ referenceName + "não foi inicializada");
            System.exit(-1);
      }
      VariableAttribution var = this.tab.get(referenceName);
      return new Variable(var.getType(), var.getVariableSerialID());
  }

  public boolean exists(String referenceName) {
        return this.tab.containsKey(referenceName);
  }
  public String getType(String referenceName) {
      if (this.tab.containsKey(referenceName)) {
            return this.tab.get(referenceName).getType();
      }
      return "";
  }
  public String toString() {
        return this.tab.toString();
  }
}