package com.example.test;

import com.twca.crypto.twcalib;

class TwCaV2 implements ITwCa{
    @Override
    public Class<?> getLibraryClass() {
        return twcalib.class;
    }
}
