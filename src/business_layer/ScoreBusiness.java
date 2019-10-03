/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_layer;

import database_layer.ScoreDB;
import java.util.ArrayList;
import java.util.List;
import model.ScoreModel;
import org.apache.commons.lang3.StringUtils;

/**
 * Business class of score
 */
public class ScoreBusiness {

    private final ScoreDB scoreDB = new ScoreDB();

    /**
     * Get scores info by search condition
     *
     * @param studentID student ID
     * @param subjectID subject ID
     * @return List of scores info
     */
    public List<ScoreModel> searchScore(String studentID, String subjectID) {
        // When studentID and subjectID are not blank
        if (StringUtils.isNoneBlank(studentID, subjectID)) {
            List<ScoreModel> scores = new ArrayList<>();
            ScoreModel model = scoreDB.getByID(studentID, subjectID);
            if (model != null) {
                scores.add(model);
            }
            return scores;
            // When studenID is not blank and subjectID is blank
        } else if (!StringUtils.isBlank(studentID)) {
            return scoreDB.getByStudentID(studentID);
            // When subjectID is not blank and studentID is blank
        } else if (!StringUtils.isBlank(subjectID)) {
            return scoreDB.getBySubjectID(subjectID);
            // When all blank
        } else {
            return scoreDB.getAll();
        }
    }

    /**
     * Get all scores info
     *
     * @return List of scores
     */
    public List<ScoreModel> getAll() {
        return scoreDB.getAll();
    }

    /**
     * Insert a score info into QLDiem table
     *
     * @param score score info
     * @return rows inserted
     */
    public int insert(ScoreModel score) {
        return scoreDB.insert(score);
    }

    /**
     * Update score info
     *
     * @param score score info
     * @return rows updated
     */
    public int update(ScoreModel score) {
        return scoreDB.update(score);
    }

    /**
     * Delete score info
     *
     * @param score score info
     * @return rows deleted
     */
    public int delete(ScoreModel score) {
        return scoreDB.delete(score);
    }
}
