package com.fise.service.event;

import com.fise.base.Page;
import com.fise.base.Response;
import com.fise.model.param.EventQueryParam;

public interface IEventService {

    Response query(Page<EventQueryParam> param);

}
