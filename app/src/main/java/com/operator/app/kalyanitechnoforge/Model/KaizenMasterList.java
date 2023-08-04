package com.operator.app.kalyanitechnoforge.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class KaizenMasterList {
    @SerializedName("activity")

    private Map<String, String> activity;

    @SerializedName("loss_number")
    private Map<String, String> lossNumber;
    @SerializedName("result")
    private Map<String, String> result;
    @SerializedName("status")
    private Map<String, String> status;

    @SerializedName("cost_incurred")
    private Map<String, String> costIncurred;

    @SerializedName("cost_incurred_frequency")
    private Map<String, String> costIncurredFrequency;

    @SerializedName("is_kaizen_mp_worthy")
    private Map<String, String> isKaizenMpWorthy;

    @SerializedName("sustenance_frequency")
    private Map<String, String> sustenanceFrequency;

    @SerializedName("area_department_zone")
    private Map<String, String> areaDepartmentZone;

    public Map<String, String> getActivity() {
        return activity;
    }

    public void setActivity(Map<String, String> activity) {
        this.activity = activity;
    }

    public Map<String, String> getLossNumber() {
        return lossNumber;
    }

    public void setLossNumber(Map<String, String> lossNumber) {
        this.lossNumber = lossNumber;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public Map<String, String> getStatus() {
        return status;
    }

    public void setStatus(Map<String, String> status) {
        this.status = status;
    }

    public Map<String, String> getCostIncurred() {
        return costIncurred;
    }

    public void setCostIncurred(Map<String, String> costIncurred) {
        this.costIncurred = costIncurred;
    }

    public Map<String, String> getCostIncurredFrequency() {
        return costIncurredFrequency;
    }

    public void setCostIncurredFrequency(Map<String, String> costIncurredFrequency) {
        this.costIncurredFrequency = costIncurredFrequency;
    }

    public Map<String, String> getIsKaizenMpWorthy() {
        return isKaizenMpWorthy;
    }

    public void setIsKaizenMpWorthy(Map<String, String> isKaizenMpWorthy) {
        this.isKaizenMpWorthy = isKaizenMpWorthy;
    }

    public Map<String, String> getSustenanceFrequency() {
        return sustenanceFrequency;
    }

    public void setSustenanceFrequency(Map<String, String> sustenanceFrequency) {
        this.sustenanceFrequency = sustenanceFrequency;
    }

    public Map<String, String> getAreaDepartmentZone() {
        return areaDepartmentZone;
    }

    public void setAreaDepartmentZone(Map<String, String> areaDepartmentZone) {
        this.areaDepartmentZone = areaDepartmentZone;
    }
}
