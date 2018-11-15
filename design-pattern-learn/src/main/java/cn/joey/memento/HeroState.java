package cn.joey.memento;

import java.util.Date;

/**
 * @auther liujiji
 * @date 2018/10/19 10:03
 */
public class HeroState implements Cloneable {
    /**
     * 英雄生命值
     */
    private int HP;
    /**
     * 英雄魔法值
     */
    private int MP;
    /**
     * 状态保存时间
     */
    private Date stateDate;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public HeroState clone() {
        try {
            return (HeroState) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}