package Render;

import java.util.LinkedList;
import java.util.List;

import Render.Expressions.AbstractExpression;

public abstract class AbstractRenderElement implements RenderElementInterface {
  private LinkedList<RenderElementInterface> children = new LinkedList<RenderElementInterface>();

  public void addChild(RenderElementInterface child) {
    this.children.add(child);
  }

  public void addAllChildren(LinkedList<RenderElementInterface> children) {
    this.children.addAll(children);
  }

  public void addAllExpChildren(LinkedList<AbstractExpression> children) {
    this.children.addAll(children);
  }

  public static String renderCollection(LinkedList<RenderElementInterface> children) {
    String childrenRender = "";
    for(RenderElementInterface child : children) {
      childrenRender += child.render();
    }
    return childrenRender;
  }

  public String renderChildren() {
    return AbstractRenderElement.renderCollection(this.children);
  }

  abstract public String render();
}