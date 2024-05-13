import React, { useEffect, useState } from 'react';
import {useParams} from "react-router-dom";
import GameCard from "../components/GameCard";
import '../styles/MatchInfo.scss';
import YearPicker from "../components/YearPicker";
import {Link} from "react-router-dom";

function MatchInfo(props) {

    const [games, setGames] = useState([]);
    const {teamName, year} = useParams();

    useEffect(() => {
        const fetchMatches = async()=>{
            const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
            const data = await response.json();

            console.log(data);
            setGames(data);
        };

        fetchMatches();
    }, [teamName, year]);

    return (
        <div className="MatchInfo">
            <div className="years">
                <YearPicker teamName={teamName}/>
            </div>
            <div>
                <h1 className="heading">{teamName}/{year}</h1>
                {games && games.map(game=><GameCard teamName={teamName} game={game}/>)}
            </div>
        </div>
    );
}

export default MatchInfo;