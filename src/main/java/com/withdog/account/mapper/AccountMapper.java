package com.withdog.account.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {

	public List<Map<String, Object>> selectTestList();
}
