import React, {PureComponent} from 'react';
import {Link} from "react-router-dom";

class Hello extends PureComponent {
    render() {
        return (
            <div>
                Hello! go to room:
                <Link to={'/57a3d647-2aea-4493-aafe-59f7ca9b57d9'}>57a3d647-2aea-4493-aafe-59f7ca9b57d9</Link>
            </div>
        );
    }
}

export default Hello;
