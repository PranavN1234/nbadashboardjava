import React from 'react';
import {Link} from "react-router-dom";
import '../styles/Information.scss';
const Information = ({teamName, game})=> {

    const otherTeam = game.team1 === teamName ? game.team2 : game.team1;
    const otherTeamPath = `/teams/${otherTeam}`;
    const isMatchWon = teamName.toLowerCase() === game.winner.toLowerCase();
    return (
        <div className={isMatchWon?'Information won-card': 'Information lost-card'}>

            <div>
                <span className="vs">vs</span><h1><Link to={otherTeamPath}>{otherTeam}</Link></h1>

                <h2 className="game-date">{game.date}</h2>
                <h3 className="game-season">Season: {game.season}</h3>

                <h3 className="match-winner">Match won by {game.winner}</h3>

                <h3 className="score">Score: {game.team1} {game.ptsHome}-{game.team2} {game.ptsAway}</h3>
            </div>

            <div className="extra-information">
                <div className="stats-card">
                    <h3>Home Team Stats:</h3>
                    <h4>Field Goal Percentage</h4>
                    <p>{game.fgHome}</p>
                    <h4>Free Throw Percentage</h4>
                    <p>{game.ftHome}</p>
                    <h4>Three Point Field Goal Percentage</h4>
                    <p>{game.f3Home}</p>
                    <h4>Assists:</h4>
                    <p>{game.astHome}</p>
                    <h4>Rebounds</h4>
                    <p>{game.rebHome}</p>
                </div>

                <div className="stats-card">
                    <h3>Away Team Stats:</h3>
                    <h4>Field Goal Percentage</h4>
                    <p>{game.fgAway}</p>
                    <h4>Free Throw Percentage</h4>
                    <p>{game.ftAway}</p>
                    <h4>Three Point Field Goal Percentage</h4>
                    <p>{game.f3Away}</p>
                    <h4>Assists:</h4>
                    <p>{game.astAway}</p>
                    <h4>Rebounds</h4>
                    <p>{game.rebAway}</p>
                </div>
            </div>

        </div>
    );
}

export default Information;