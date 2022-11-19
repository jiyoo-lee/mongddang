import { useNavigate, useParams } from "react-router-dom";
import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import LogoHeader from "../LogoHeader";
import { useEffect, useState } from "react";
import GetAxios from "../../utils/axios/GetAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";

const SearchMyPainting = () => {
    const navigate = useNavigate();
    const {contestId, userId } = useParams();
    const [ myPainting, setMyPainting ] = useState([]);

    useEffect(()=>{
        GetAxios('/arts-center/'+contestId+'/'+userId, {params:{}},(res)=>{
            setMyPainting(res.data)
            console.log(res.data)
        })
    }, [])

    const handleDeletePainting = (id)=> {
        DeleteAxios('/arts-center/painting/' + id, {params:{}}, res=>window.location.reload());
    };

    return(
        <div className="all_wrapper">
        <LogoHeader/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'진행 중'} type={'nothing'} onClick={()=>{navigate('/home/feed')}}/>
                <MyButton text={'수상작'} onClick={()=>{navigate('/arts-center/winners')}}/>
            </div>
            <div className="home_wrapper">
                <br/>
                <br/>
                {myPainting && myPainting.map(painting => 
                    <div key={painting.paintingId}>
                         <div className="items">
                        <img  className="items_img" src={painting.contestPaintingUrl} alt="contest_painting" />
                        </div>
                        <div className="items_desc">
                        <p className="items_title">{painting.title}</p>
                        <p className="items_content">{painting.description}</p>
                        <button onClick={()=>(handleDeletePainting(painting.paintingId))}>삭제</button>
                    </div>
                    </div>
                )}
            </div>
        </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    );
}

export default SearchMyPainting;