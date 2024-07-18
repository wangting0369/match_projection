package com.zixian.matchprojection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zixian.matchprojection.model.Tag;
import com.zixian.matchprojection.service.TagService;
import com.zixian.matchprojection.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 56308
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-07-09 01:51:56
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

}




