import React from 'react';
import ProductList from "./ProductCard";
import product1 from "./product1.jpeg"
import product2 from "./product2.jpeg"
import {Container} from "react-bootstrap";


const products = [
    {
        id: 1,
        name: 'Product 1',
        description: 'This is product 1',
        price: 10,
        image: product1,
    },
    {
        id: 2,
        name: 'Product 2',
        description: 'This is product 2',
        price: 20,
        image: product2,
    },
    // Diğer ürünler...
];

const Products = () => {
    return (
        <Container>
            <h1>Product List</h1>
            <ProductList products={products} />
        </Container>
    );
};

export default Products;
