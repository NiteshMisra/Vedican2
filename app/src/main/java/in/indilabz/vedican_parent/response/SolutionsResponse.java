package in.indilabz.vedican_parent.response;

import com.google.gson.annotations.SerializedName;
import in.indilabz.vedican_parent.model.Solutions;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SolutionsResponse {

    @SerializedName("error")
    private String error;

    @Nullable
    @SerializedName("result")
    private ArrayList<Solutions> solutions;

    public SolutionsResponse(String error, @Nullable ArrayList<Solutions> solutions) {
        this.error = error;
        this.solutions = solutions;
    }

    public String getError() {
        return error;
    }

    @Nullable
    public ArrayList<Solutions> getSolutions() {
        return solutions;
    }
}
