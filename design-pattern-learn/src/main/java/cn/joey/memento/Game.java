package cn.joey.memento;

/**
 * @auther liujiji
 * @date 2018/10/19 9:55
 * 游戏本身，备份的是游戏的状态
 */
public class Game {

    /**
     * 英雄属性
     */
    private HeroState hState;
    /**
     * 场景状态属性
     */
    private SceneState sState;

    public HeroState gethState() {
        return hState;
    }

    public void sethState(HeroState hState) {
        this.hState = hState;
    }

    public SceneState getsState() {
        return sState;
    }

    public void setsState(SceneState sState) {
        this.sState = sState;
    }

    /**
     * 备份游戏
     * @return
     */
    public GameMemento createMemento(){
        return new GameMemento(hState,sState);
    }

    /**
     * 玩游戏
     */
    public void play(){
        hState.setHP(0);
        hState.setMP(0);
        sState.setCoin(0);
        sState.setWood(0);
    }

    /**
     * 游戏还原
     * @param gameMemento
     */
    public void restore(GameMemento gameMemento) {
        this.hState = gameMemento.gethState();
        this.sState = gameMemento.getsState();
    }
}
