package ru.asgubin.rsrdb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Params")
public class Param {

    @Id
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

    @Column(name = "Mean")
    private float mean;

    @Column(name = "DistType")
    private short distType;

    @Column(name = "DistPar1")
    private float distPar1;

    @Column(name = "DistPar2")
    private float distPar2;

    @Column(name = "DistPar3")
    private float distPar3;

    @Column(name = "DistType")
    private short unit;

    @Column(name = "Median")
    private float median;

    @Column(name = "P05")
    private float p05;

    @Column(name = "P95")
    private float p95;

    @Column(name = "VarCoeff")
    private float varCoeff;

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

    public Param() {
    }

    public Param(short type, int num, String id, String text, short tag,
                 float mean, short distType, float distPar1, float distPar2,
                 float distPar3, short unit, float median, float p05, float p95,
                 float varCoeff, Date editDate, int editUid, Date reviewDate,
                 int reviewUid, Date approvedDate, int approvedUid, boolean flag) {
        this.type = type;
        this.num = num;
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.mean = mean;
        this.distType = distType;
        this.distPar1 = distPar1;
        this.distPar2 = distPar2;
        this.distPar3 = distPar3;
        this.unit = unit;
        this.median = median;
        this.p05 = p05;
        this.p95 = p95;
        this.varCoeff = varCoeff;
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

    public float getMean() {
        return mean;
    }

    public void setMean(float mean) {
        this.mean = mean;
    }

    public short getDistType() {
        return distType;
    }

    public void setDistType(short distType) {
        this.distType = distType;
    }

    public float getDistPar1() {
        return distPar1;
    }

    public void setDistPar1(float distPar1) {
        this.distPar1 = distPar1;
    }

    public float getDistPar2() {
        return distPar2;
    }

    public void setDistPar2(float distPar2) {
        this.distPar2 = distPar2;
    }

    public float getDistPar3() {
        return distPar3;
    }

    public void setDistPar3(float distPar3) {
        this.distPar3 = distPar3;
    }

    public short getUnit() {
        return unit;
    }

    public void setUnit(short unit) {
        this.unit = unit;
    }

    public float getMedian() {
        return median;
    }

    public void setMedian(float median) {
        this.median = median;
    }

    public float getP05() {
        return p05;
    }

    public void setP05(float p05) {
        this.p05 = p05;
    }

    public float getP95() {
        return p95;
    }

    public void setP95(float p95) {
        this.p95 = p95;
    }

    public float getVarCoeff() {
        return varCoeff;
    }

    public void setVarCoeff(float varCoeff) {
        this.varCoeff = varCoeff;
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
