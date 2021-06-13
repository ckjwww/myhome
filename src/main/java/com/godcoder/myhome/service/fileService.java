package com.godcoder.myhome.service;

import com.godcoder.myhome.entity.fileDTO;
import com.godcoder.myhome.mapper.fileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class fileService {

    @Autowired
    fileMapper filemapper;

    public fileDTO retrieveFile(String fileId){

        fileDTO fileDto = filemapper.retrieveFile(fileId);

        return fileDto;
    }

    public HashMap insertBaseball(fileDTO fileDto){

        int rtnCnt = filemapper.insertFile(fileDto);

        HashMap rtnMap = new HashMap<String, Object>();
        rtnMap.put("rtnCnt", rtnCnt);

        return rtnMap;
    }
}
