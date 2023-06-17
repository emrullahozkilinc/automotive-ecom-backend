import React from 'react';
import {Collapse, Nav, Navbar, NavItem, NavLink} from "react-bootstrap";

function NavBar(props) {
    return (
        <Navbar color="light" light expand="md">
            <Navbar.Brand href="/">reactstrap</Navbar.Brand>
            <Navbar.Toggle />
            <Collapse navbar>
                <Nav className="ml-auto" navbar>
                    <NavItem>
                        <NavLink href="/components/">Components</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href="https://github.com/reactstrap/reactstrap">GitHub</NavLink>
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>
    );
}

export default NavBar;