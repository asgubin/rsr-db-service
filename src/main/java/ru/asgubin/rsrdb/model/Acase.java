package ru.asgubin.rsrdb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Acase")
public class Acase {

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

    @Column(name = "ResType")
    private short resType;

    @Column(name = "Mean")
    private double mean;

    @Column(name = "P05")
    private double p05;

    @Column(name = "P95")
    private double p95;

    @Column(name = "TextRes")
    private short textRes;

    @Column(name = "GERes")
    private short geRes;

    @Column(name = "BERes")
    private short beRes;

    @Column(name = "ExchRes")
    private short exchRes;

    @Column(name = "Unit")
    private short unit;

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

    public Acase() {
    }

    public Acase(short type, int num, String id, String text, short tag, short resType,
                 double mean, double p05, double p95, short textRes, short geRes, short beRes,
                 short exchRes, short unit, Date editDate, int editUid, Date reviewDate,
                 int reviewUid, Date approvedDate, int approvedUid, boolean flag) {
        this.type = type;
        this.num = num;
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.resType = resType;
        this.mean = mean;
        this.p05 = p05;
        this.p95 = p95;
        this.textRes = textRes;
        this.geRes = geRes;
        this.beRes = beRes;
        this.exchRes = exchRes;
        this.unit = unit;
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

    public short getResType() {
        return resType;
    }

    public void setResType(short resType) {
        this.resType = resType;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getP05() {
        return p05;
    }

    public void setP05(double p05) {
        this.p05 = p05;
    }

    public double getP95() {
        return p95;
    }

    public void setP95(double p95) {
        this.p95 = p95;
    }

    public short getTextRes() {
        return textRes;
    }

    public void setTextRes(short textRes) {
        this.textRes = textRes;
    }

    public short getGeRes() {
        return geRes;
    }

    public void setGeRes(short geRes) {
        this.geRes = geRes;
    }

    public short getBeRes() {
        return beRes;
    }

    public void setBeRes(short beRes) {
        this.beRes = beRes;
    }

    public short getExchRes() {
        return exchRes;
    }

    public void setExchRes(short exchRes) {
        this.exchRes = exchRes;
    }

    public short getUnit() {
        return unit;
    }

    public void setUnit(short unit) {
        this.unit = unit;
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
