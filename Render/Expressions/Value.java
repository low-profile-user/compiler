package Render.Expressions;


public class Value extends AbstractExpression {
  private String value;
  
  public Value(String type, String value) {
    this.setType(type);
    this.setValue(value);
  }

  public Value(String type) {
    this.setType(type);
    this.setDefault();
  }

  private void setDefault() {
    if(this.type.equals("string")) {
      this.value = "\"\"";
    } else {
      this.setValue("0");;
    }
  }

  public void setValue(String value) {
    switch (this.type) {
      case "double":
      case "float":
        if (!value.contains(".")) {
          this.value = value + ".0";
        } else {
          this.value = value;
        }
        break;
      case "int":
      case "long":
        if (!value.contains(".")) {
          this.value = value.split(".")[0];
        } else {
          this.value = value;
        }
        break;
      default:
        this.value = value;
        break;
    }
  }

  public String render() {
    String slotSize = this.getSlotSize() > 1 ? ""+this.getSlotSize() : "";
    return "ldc"+slotSize+"_w "+this.value + "\r\n" + this.getConversionByteCode();
  }
}
