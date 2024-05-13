import React from 'react';
import {Link} from "react-router-dom";
import '../styles/GameCard.scss';
const GameCard =({teamName, game})=>{

    const otherTeam = game.team1 === teamName?game.team2:game.team1;
    const otherTeamPath = `/teams/${otherTeam}`;
    const isMatchWon = teamName.toLowerCase() === game.winner.toLowerCase();
    return (
        <div className={isMatchWon?'GameCard won-card': 'GameCard lost-card'}>
            <h3>vs <Link to={otherTeamPath}>{otherTeam}</Link></h3>

            <p>Date: {game.date}</p>
            <p>Match won by {game.winner}</p>

            <p>Score: {game.team1} {game.ptsHome}-{game.team2} {game.ptsAway}</p>
        </div>
    );
}

export default GameCard;