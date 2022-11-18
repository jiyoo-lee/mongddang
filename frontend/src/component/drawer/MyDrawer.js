import { useState } from "react";
import { useNavigate } from "react-router-dom";
import DeleteAxios from "../../utils/axios/DeleteAxios";
import MyButton from "../button/MyButton";

const MyDrawer = ({state,info}) => {

    const navigate = useNavigate();

    const goToPage = (dropsId) => {
        navigate('/drawer/paintings',{state:{dropsId:dropsId}})
    }

    const onDeleteDrops = (dropsId) =>{
        window.confirm("드랍 삭제시 관련 그림도 모두 삭제됩니다. 진행하시겠습니까?") ? 
            DeleteAxios('/drops/'+dropsId, {params:{}},(res)=>{window.location.reload()})
            : <></>
    }

    return (
        <div className="drawer_wrapper">
            <span className="userid_message">반가워요, {sessionStorage.getItem("userId")}님!</span>
            <br/>
            <span className="drawer_msg"> 드랍 개수 : </span> <span className="userid_message">{state.paintingCount} </span>
            <span className="drawer_msg"> 그림 개수 : </span>
            {state.paintingCountGroupingGenre && state.paintingCountGroupingGenre.map(genre => (
                <span key={genre.genreName}>{genre.genreName} : <span className="userid_message">{genre.count} </span></span>
            ))}
            <MyButton text={'드랍 생성'} onClick={()=>{navigate('/home/upload')}}/>
            
        <br/>
        <br/>
        <br/>
        <br/>
        <div>
                {state.drops && state.drops.map(drops=>(
                    <div key={drops.dropsId} className="drops_list">
                        <br/>
                        <img className="items_img" src={drops.lastPaintingUrl} onClick={()=>goToPage(drops.dropsId)}/> <br/>
                        <span className="genre">{drops.dropsGenre}</span> <br/><br/>
                        <span className="drawer_msg">{drops.dropsName}</span> <br/><br/>
                        <img className="icon" src="../.././img/like.png" alt="like"/> {drops.mongddangCount}
                        <MyButton type={'negative'} text={'삭제'} onClick={()=>onDeleteDrops(drops.dropsId)}/>
                    </div>
                ))}
            </div>
            <br/>
        </div>
    );
}

export default MyDrawer;