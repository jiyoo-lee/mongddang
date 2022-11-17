import { useEffect  } from "react";
import {useParams ,useNavigate} from "react-router-dom";
import GetAxios from "../../../utils/axios/GetAxios";
import { useState } from "react";

const OtherLikeList = () => {
    const { userId } = useParams();
    const [likes, setLikes] = useState([]);
    const navigate = useNavigate();

    useEffect(()=>{
        GetAxios('/painting/like/'+userId,{params:{}},(res)=>{setLikes(res.data)})
    },[])

    const goToPage = (memberId) => {
        navigate('/drawer/'+memberId)
    }

    return(
        <div className="drawer_wrapper">
            <span className="userid_message">{userId}님이 좋아요한 순간들</span>
            <br/>
        <br/>
        <br/>
        { likes && likes.length > 0 ? likes.map(like => (
            <div key={like.paintingUrl} className="drops_list">
                <img className="items_img" src={like.paintingUrl} onClick={()=>goToPage(like.memberId)}/> <br/>
                <span className="drawer_msg">{like.name}</span> <br/><br/>
            </div>
        )): <></>}
        </div>
    );
}

export default OtherLikeList;