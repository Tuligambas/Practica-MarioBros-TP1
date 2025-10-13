package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Goomba extends GameObject {
  private static final String ICON = Messages.GOOMBA;
  private boolean isAlive;
  private Action dir;
  private boolean falling;

  public Goomba(Position position, Game game) {
    super(position, game);
    this.isAlive = true;
    this.dir = Action.LEFT;
  }

  @Override
  public void update() {
    // si es solido abajo dos opciones
    if (solidBelow()) {
      // primero veo si estaba cayendo
      if (falling) {
        falling = false;
      }
      // si es solido a la izquierda o derecha cambia de direccion o pared
      if (solidNextTo(dir) || wallNextTo(dir)) {
        dir = dir.opposite();
      }
      // si no es solido a la izquierda o derecha se mueve
      else {
        move();
      }
    }
    // si no es solido abajo cae
    else {
      this.falling = true;
      fall();
    }

    // chequea si esta en una posicion valida
    checkPosition();
  }

  private void move() {
    this.pos = this.pos.move(dir);
  }

  public String getIcon() {
    return ICON;
  }

  @Override
  public boolean isAlive() {
    return isAlive;
  }

  public Action geAction() {
    return this.dir;
  }

  private void fall() {
    this.pos = this.pos.move(Action.DOWN);
    if (solidBelow()) {
      this.falling = false;
    }

  }

  public void setAlive(boolean alive) {
    this.isAlive = alive;
  }

  @Override
  public boolean receiveInteraction(GameObject other) {
    return other.interactWith(this);
  }

  @Override
  protected void checkPosition() {
    if (!this.pos.isInBoard()) {
      setAlive(false);
    }
  }

  public Object getAction() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAction'");
  }

}