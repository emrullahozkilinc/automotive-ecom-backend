import React from 'react';
import {Card, CardImg, Col, Row} from "react-bootstrap";

const ProductCard = ({ product }) => {
    return (
        <Card>
            <CardImg top width="200" height="200" src={product.image} alt={product.name} />
            <Card.Body>
                <Card.Title>{product.name}</Card.Title>
                <Card.Text>{product.description}</Card.Text>
                <Card.Text>Price: ${product.price}</Card.Text>
            </Card.Body>
        </Card>
    );
};

const ProductList = ({ products }) => {
    return (
        <Row>
            {products.map((product) => (
                <Col sm={3}>
                    <ProductCard key={product.id} product={product} />
                </Col>
            ))}
        </Row>
    );
};

export default ProductList;
