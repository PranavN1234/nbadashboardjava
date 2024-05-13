import React from 'react';
import '../styles/Team.scss';
import {Link} from "react-router-dom";

function Team({teamName}) {
    return (
        <div className="Team">
            <h1><Link to={`/teams/${teamName}`}>{teamName}</Link></h1>
        </div>
    );
}

export default Team;