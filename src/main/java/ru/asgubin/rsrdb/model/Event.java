package ru.asgubin.rsrdb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Events")
public class Event {

    @Column(name = "Type", nullable = false)
    private short type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Num", nullable = false, unique = true)
    private int num;

    @Id
    @Column(name = "ID", length = 20, nullable = false)
    private String id;

    @Column(name = "Text", length = 100)
    private String text;

    @Column(name = "Tag")
    private short tag;

    @Column(name = "Symbol")
    private short symbol;

    @Column(name = "Model")
    private short model;

    @Column(name = "State")
    private short state;

    @Column(name = "CalcType")
    private short calcType;

    @Column(name = "Mean")
    private double mean;

    @Column(name = "InitEnabl")
    private short initEnabl;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EditData")
    private Date editDate;

    @Column(name = "EditUid")
    private int editUid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ReviewData")
    private Date reviewDate;

    @Column(name = "ReviewUid")
    private int reviewUid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ApprovedData")
    private Date approvedDate;

    @Column(name = "ApprovedUid")
    private int approvedUid;

    @Column(name = "flag")
    private boolean flag;

    public Event() {
    }

    public Event(short type, int num, String id, String text, short tag, short symbol,
                 short model, short state, short calcType, double mean, short initEnabl,
                 Date editDate, int editUid, Date reviewDate, int reviewUid,
                 Date approvedDate, int approvedUid, boolean flag) {
        this.type = type;
        this.num = num;
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.symbol = symbol;
        this.model = model;
        this.state = state;
        this.calcType = calcType;
        this.mean = mean;
        this.initEnabl = initEnabl;
        this.editDate = editDate;
        this.editUid = editUid;
        this.reviewDate = reviewDate;
        this.reviewUid = reviewUid;
        this.approvedDate = approvedDate;
        this.approvedUid = approvedUid;
        this.flag = flag;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public short getTag() {
        return tag;
    }

    public void setTag(short tag) {
        this.tag = tag;
    }

    public short getSymbol() {
        return symbol;
    }

    public void setSymbol(short symbol) {
        this.symbol = symbol;
    }

    public short getModel() {
        return model;
    }

    public void setModel(short model) {
        this.model = model;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public short getCalcType() {
        return calcType;
    }

    public void setCalcType(short calcType) {
        this.calcType = calcType;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public short getInitEnabl() {
        return initEnabl;
    }

    public void setInitEnabl(short initEnabl) {
        this.initEnabl = initEnabl;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public int getEditUid() {
        return editUid;
    }

    public void setEditUid(int editUid) {
        this.editUid = editUid;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getReviewUid() {
        return reviewUid;
    }

    public void setReviewUid(int reviewUid) {
        this.reviewUid = reviewUid;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public int getApprovedUid() {
        return approvedUid;
    }

    public void setApprovedUid(int approvedUid) {
        this.approvedUid = approvedUid;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
