package io.superstore.services;

import io.superstore.data.Basket;

/**
 * An Interface for Calculator Services
 */
public interface CalculatorService
{
    long calculateTotal(Basket basket);
}
