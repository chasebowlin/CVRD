package com.cvrd.core.common.interfaces.database;

import com.cvrd.core.common.models.BaseDataModel;
import java.util.HashMap;
import java.util.List;

/**
 * the base interface that all repositories
 * will inherit from to connect to the
 * underlying database
 *
 */
public interface IDatabaseInterface {

    /**
     * writes a single data model to the table
     *
     * @param model the model being written
     * @return returns a success message
     */
    String create(BaseDataModel model);

    /**
     * writes multiple data models to the table
     *
     * @param data the models being written
     * @return returns a success message
     */
    String create(List<BaseDataModel> data);


    /**
     * reads from the underlying database based on
     * the query passed in
     *
     * @param primaryKey
     * @param sortKey
     * @return the list of data
     */
    List<BaseDataModel> readFromTable(String primaryKey, String sortKey);

    /**
     * removes a single item from the database
     *
     * @param primaryKey
     * @param sortKey
     * @return
     */
    BaseDataModel remove(String primaryKey, String sortKey);

    /**
     * updates a single item
     *
     * @param model
     * @return
     */
    BaseDataModel update(BaseDataModel model);

}
