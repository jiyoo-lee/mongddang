import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";

const OtherDrawer = () => {
    const [state, setState] = useState([]);
    const { userId } = useParams();

    useEffect(()=>{
        console.log(userId);

        GetAxios('/drawer/' + userId, {param:{}}, (res)=>{
            console.log(res.data);
            setState(res.data);
        });
    }, []);

    return (
    <div className="all_wrapper">
        <div>
            {userId}의 그림 개수 : {state.paintingCount}
        </div>
        <br/>
        <div>
            {userId}의 장르별 그림 개수
            {state.paintingCountGroupingGenre && state.paintingCountGroupingGenre.map(genre => (
                <div key={genre.genreName}>{genre.genreName} : {genre.count}</div>
            ))}
        </div>
        <br/>
        <div>
            {userId}의 드랍
            {state.drops && state.drops.map(drops=>(
                <div key={drops.dropsId}>
                    <img className="items_img" src={drops.lastPaintingUrl}/> <br/>
                    드랍이름 : {drops.dropsName} <br/>
                    드랍장르 : {drops.dropsGenre} <br/>
                    몽땅개수 : {drops.mongddangCount}
                </div>
            ))}
        </div>
    </div>
    )
}

export default OtherDrawer;