package com.DespairBlueGmailCom.BeacgotchuOdg;

/**
 * Created by kietsy on 15/11/15.
 */
public class Beacgotchu {
    public
    int needToEat = 0;
    int needToPee = 0;
    int needToPlay = 0;
    boolean alive = true;

    public void step() {
        if (this.alive) {
          this.needToEat++;
          this.needToPee++;
          this.needToPlay++;
        }

        if (this.needToPee > 100 || this.needToEat > 100) {
            this.alive = false;
        }
    }

    public void crap() {
        if (this.alive) {
            if (this.needToPee >= 10) {
                this.needToPee = this.needToPee - 10;
            }
        }
    }

    public void eat() {
        if (this.alive) {
            if (this.needToEat >= 10) {
                this.needToEat = this.needToEat - 10;
            }
      }
    }

    public void play() {
      if (this.alive) {

        if (this.needToPlay > 0) {
            this.needToPlay = this.needToPlay - 5;
        }
        if (this.needToPlay < 0) {
          this.alive = false;
        }
      }
    }
}
