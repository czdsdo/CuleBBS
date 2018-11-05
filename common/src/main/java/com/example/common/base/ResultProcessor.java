package com.example.common.base;

import com.example.common.dto.CuleResult;

@FunctionalInterface
public interface ResultProcessor {
    CuleResult process();
}
