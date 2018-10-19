package cn.joey.memento;

/**
 * @auther liujiji
 * @date 2018/10/19 9:59
 */
public class GameMemento {
    private HeroState hState;
    private SceneState sState;
    public GameMemento(HeroState hState,SceneState sState) {
        this.hState = hState.clone();
        this.sState = sState.clone();
    }

    /**
     * 获取备份状态
     * @return
     */
    public HeroState gethState() {
        return this.hState;
    }

    /**
     * 获取备份状态
     * @return
     */
    public SceneState getsState(){
        return this.sState;
    }
}
