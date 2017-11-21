package gameofthrones.yuriydopa.com.gameofthrones.data.storages.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import gameofthrones.yuriydopa.com.gameofthrones.data.network.responce.CharactertResp;

import static gameofthrones.yuriydopa.com.gameofthrones.utils.EntityUtils.cutIdFromUrl;

/**
 * Created by yuriy on 20.02.17.
 */

@Entity(active = true, nameInDb = "members")
public class CharacterEntity {

    @Id
    private Long id;

    private String remoteId;

    private String remoteHouseId;

    private String name;

    private String words;

    private String born;

    private String died;

    @ToMany(joinProperties = {@JoinProperty(name = "remoteId", referencedName = "memberRemoteId")})
    private List<TitleEntity> titles;

    @ToMany(joinProperties = {@JoinProperty(name = "remoteId", referencedName = "memberRemoteId")})
    private List<AliasEntity> aliases;

    private String father;

    private String mother;

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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 731614754)
    public synchronized void resetAliases() {
        aliases = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1418375086)
    public List<AliasEntity> getAliases() {
        if (aliases == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AliasEntityDao targetDao = daoSession.getAliasEntityDao();
            List<AliasEntity> aliasesNew = targetDao._queryCharacterEntity_Aliases(remoteId);
            synchronized (this) {
                if(aliases == null) {
                    aliases = aliasesNew;
                }
            }
        }
        return aliases;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1506933621)
    public synchronized void resetTitles() {
        titles = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 648905432)
    public List<TitleEntity> getTitles() {
        if (titles == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TitleEntityDao targetDao = daoSession.getTitleEntityDao();
            List<TitleEntity> titlesNew = targetDao._queryCharacterEntity_Titles(remoteId);
            synchronized (this) {
                if(titles == null) {
                    titles = titlesNew;
                }
            }
        }
        return titles;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 35488055)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCharacterEntityDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 1738962208)
    private transient CharacterEntityDao myDao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public String getMother() {
        return this.mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return this.father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getDied() {
        return this.died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public String getBorn() {
        return this.born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getWords() {
        return this.words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemoteHouseId() {
        return this.remoteHouseId;
    }

    public void setRemoteHouseId(String remoteHouseId) {
        this.remoteHouseId = remoteHouseId;
    }

    public String getRemoteId() {
        return this.remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 346942233)
    public CharacterEntity(Long id, String remoteId, String remoteHouseId, String name, String words,
            String born, String died, String father, String mother) {
        this.id = id;
        this.remoteId = remoteId;
        this.remoteHouseId = remoteHouseId;
        this.name = name;
        this.words = words;
        this.born = born;
        this.died = died;
        this.father = father;
        this.mother = mother;
    }

    public CharacterEntity(CharactertResp response, String remoteHouseId, String words) {
        this.remoteId = cutIdFromUrl(response.getUrl());
        this.remoteHouseId = remoteHouseId;
        this.name = response.getName();
        this.words = words;
        this.born = response.getBorn();
        this.died = response.getDied();
        List<TitleEntity> titles = new ArrayList<>(response.getTitles().size());
        for (String title : response.getTitles()) titles.add(new TitleEntity(title, remoteId));
        this.titles = titles;
        List<AliasEntity> aliases = new ArrayList<>(response.getAliases().size());
        for (String alias : response.getAliases()) aliases.add(new AliasEntity(alias, remoteId));
        this.aliases = aliases;
        this.mother = response.getMother();
        this.father = response.getFather();
    }

    @Generated(hash = 788197839)
    public CharacterEntity() {
    }
}
