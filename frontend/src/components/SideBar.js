import React from 'react';
import {Col, Container, Nav, Row} from 'react-bootstrap';
import "../style/sidenav.css"

const Sidebar = () => {
    return (
        <Nav className="flex-column">
            <Nav.Link href="#home">Ana Sayfa</Nav.Link>
            <Nav.Link href="#products">Ürünler</Nav.Link>
            <Nav.Link href="#about">Hakkımızda</Nav.Link>
            <Nav.Link href="#contact">İletişim</Nav.Link>
        </Nav>
    );
};

const App = () => {
    return (
        <div>
            <h1>Automotive-Ecom</h1>
            <Container>
                <Row>
                    <Col md={3}>
                        <Sidebar />
                    </Col>
                    <Col md={9}>
                        {/* İçerik */}
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default App;
