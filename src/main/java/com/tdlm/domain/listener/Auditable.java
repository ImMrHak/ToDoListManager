package com.tdlm.domain.listener;

import java.util.Date;

public interface Auditable {
    void setCreatedAt(Date createdAt);
    void setUpdatedAt(Date updatedAt);
}
