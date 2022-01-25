package com.mytaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.mytaxi.entity.CarSequences;
import com.mytaxi.entity.DriverSequences;

@Service
public class NextSequenceService {
  @Autowired
  private MongoOperations mongoOperations;

  public long getNextCarSequence(String seqName) {
    Query query = new Query(Criteria.where("_id").is(seqName));
    Update update = new Update();
    update.inc("seq", 1);
    FindAndModifyOptions options = new FindAndModifyOptions();
    options.returnNew(true);
    options.upsert(true);
    CarSequences obj =
        mongoOperations.findAndModify(query, update, options, CarSequences.class);
    return obj.getSeq();
  }
  
  public long getNextDriverSequence(String seqName) {
    Query query = new Query(Criteria.where("_id").is(seqName));
    Update update = new Update();
    update.inc("seq", 1);
    FindAndModifyOptions options = new FindAndModifyOptions();
    options.returnNew(true);
    options.upsert(true);
    DriverSequences obj =
        mongoOperations.findAndModify(query, update, options, DriverSequences.class);
    return obj.getSeq();
  }
}
