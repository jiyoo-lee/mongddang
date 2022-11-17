import { useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";

const MyDrawer = (props) => {

    const navigate = useNavigate();


    const goToPage = (dropsId) => {
        navigate('/drawer/paintings',{state:{dropsId:dropsId}})
    }

    return (
        <div className="drawer_wrapper">
            <span className="userid_message">{sessionStorage.getItem("userId")}</span> <span className="drawer_msg">님의 드랍 개수 : </span>
            <span className="userid_message"> 24 </span>
            <span className="drawer_msg">최대 몽땅 개수 : </span> <span className="userid_message"> 234 </span>
            <MyButton text={'드랍 생성'} onClick={()=>{navigate('/home/upload')}}/>
            <MyButton type={'negative'} text={'삭제'}/>
        <br/>
        <br/>
        <br/>
        <br/>
        {props.drops.map(drop => (
            <div className="drops_list" key={drop.id} >
                <img className="drops"src={drop.lastPaintingUrl} onClick={()=>goToPage(drop.id)}/>
                <span className="drawer_msg">{drop.name}</span>
            </div>
        ))}
            <br/>
        </div>
    );
}

export default MyDrawer;