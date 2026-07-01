package com.marlonmachado.dscatalog.projections;

import com.marlonmachado.dscatalog.entities.Product;

public interface ProductProjection extends IdProjection<Long> {
    String getName();
}
