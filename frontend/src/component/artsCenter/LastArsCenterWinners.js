
const LastArtsCenterWinners = ({winners}) => {
    console.log(winners)
    return (
        <div>
            {winners.map(winner=> (
                <div className="winner_card" key={winner.paintingId}>
                    <h3> winner ! </h3>
                    <img src={winner.paintingUrl} alt={winner.paintingName}/>
                    <p>{winner.paintingName}</p>
                    <span>{winner.description}</span>
                <br/>
                <br/>
                    <img className="items_profile" src={winner.profileUrl} alt={winner.memberId}/>
                    <span>{winner.nickname}({winner.memberId})</span>
                <br/>
                <br/>
                    <img className="icon" src={process.env.PUBLIC_URL + `../.././img/like.png`}/> <span>{winner.paintingMongddangCount}</span>
                </div>
            ))}
        </div>
    );
}

export default LastArtsCenterWinners;