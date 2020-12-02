package com.example.test;

import com.twca.crypto_test.twcalib;

class TwCaV1 implements ITwCa {
    @Override
    public Class<?> getLibraryClass() {
        return twcalib.class;
    }
}
