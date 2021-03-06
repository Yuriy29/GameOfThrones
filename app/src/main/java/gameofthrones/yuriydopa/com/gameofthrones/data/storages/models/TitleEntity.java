package gameofthrones.yuriydopa.com.gameofthrones.data.storages.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by yuriy on 20.02.17.
 */

@Entity(active = true, nameInDb = "titles")
public class TitleEntity {

    @Id
    private Long id;
    private String title;
    private String memberRemoteId;
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1697042185)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTitleEntityDao() : null;
    }
    /** Used for active entity operations. */
    @Generated(hash = 1141104455)
    private transient TitleEntityDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    public String getMemberRemoteId() {
        return this.memberRemoteId;
    }
    public void setMemberRemoteId(String memberRemoteId) {
        this.memberRemoteId = memberRemoteId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1927187165)
    public TitleEntity(Long id, String title, String memberRemoteId) {
        this.id = id;
        this.title = title;
        this.memberRemoteId = memberRemoteId;
    }
    public TitleEntity(String title, String memberId) {
        this.title = title;
        this.memberRemoteId = memberId;
    }

    @Generated(hash = 1736055002)
    public TitleEntity() {
    }
}
