package com.fise.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fise.base.ErrorCode;
import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.dao.AnswerMapper;
import com.fise.dao.CommentMapper;
import com.fise.dao.ProblemsMapper;
import com.fise.model.entity.Answer;
import com.fise.model.entity.Comment;
import com.fise.model.entity.CommentExample;
import com.fise.model.entity.CommentExample.Criteria;
import com.fise.model.entity.Problems;
import com.fise.service.comment.ICommentService;
import com.fise.utils.DateUtil;
import com.fise.utils.StringUtil;

@Service
public class CommentServiceImpl implements ICommentService{

    @Autowired
    CommentMapper commentDao;
    
    @Autowired
    AnswerMapper answerDao;
    
    @Autowired
    ProblemsMapper problemDao;
    
    @Override
    public Response addComment(Comment record) {
        Response res = new Response();
        
        if(StringUtil.isEmpty(record.getContent())){
            res.failure(ErrorCode.ERROR_FISE_DEVICE_PARAM_NULL);
            res.setMsg("内容不能为空");
            return res;
        }
        
        record.setUpdated(DateUtil.getLinuxTimeStamp());
        record.setCreated(DateUtil.getLinuxTimeStamp());
        
        commentDao.insertSelective(record);
        
        Answer answer=answerDao.selectByPrimaryKey(record.getAnswerId());
        answer.setCommentNum(answer.getCommentNum()+1);
        answerDao.updateByPrimaryKeySelective(answer);
        
        Problems problem=problemDao.selectByPrimaryKey(record.getProblemId());
        problem.setAnswerNum(problem.getAnswerNum()+1);
        problemDao.updateByPrimaryKeySelective(problem);
        
        return res.success();
    }

    @Override
    public Response queryComment(Page<Comment> page) {
        Response res = new Response();
        
        CommentExample example = new CommentExample();
        Criteria criteria = example.createCriteria();
        
        if(page.getParam().getAnswerId()!=null){
            criteria.andAnswerIdEqualTo(page.getParam().getAnswerId());
        }
        
        criteria.andStatusEqualTo(1);
        example.setOrderByClause("created desc");
        
        List<Comment> list=commentDao.selectByPage(example, page);
        
        res.success(list);
        return res;
    }

}
