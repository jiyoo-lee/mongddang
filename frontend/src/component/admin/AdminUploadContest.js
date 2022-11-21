import { useState,useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Datetime from 'react-datetime';
import "react-datetime/css/react-datetime.css";
import MyButton from "../button/MyButton";
import PostAxios from "../../utils/axios/PostAxios";
import axios from "axios";


const AdminUploadContest = () =>{


    const navigate = useNavigate();
    const [ title ,setTitle ] = useState("");
    const [ startDay, setStartday ] = useState(new Date());
    const [ endDay, setEndDay ] = useState(new Date());
    const [ poster , setPoster ] = useState("");


    const toggleStartDatetime = (date) =>{
        let startdates = date.format('YYYY-MM-DD')
        let starttimes = date.format('HH:mm:ss')
        let startDatetime = startdates + " "+starttimes;

        setStartday(startDatetime)
    }

    const toggleEndDatetime = (date) =>{
        let enddates = date.format('YYYY-MM-DD')
        let endtimes = date.format('HH:mm:ss')
        let endDatetime = enddates + " "+endtimes;

        setEndDay(endDatetime)
    }

    const saveFileImage = (e) =>{
        setPoster(e.target.files[0]);
    }

    const onDrop = async() => { 
        let formData = new FormData();
        formData.append("api_key", "516516722966632");
        formData.append("upload_preset", "vspuoidk");
        formData.append("timestamp", (Date.now() / 1000) | 0);
        formData.append(`file`, poster);

        const config = {
            header: { "Content-Type": "multipart/form-data" }
        }

        await axios.post('https://api.cloudinary.com/v1_1/dlcpn7mul/image/upload', formData, config)
        .then(res => { 
            const requestBody = {
                memberId : sessionStorage.getItem("userId"),
                title : title,
                posterUrl : res.data.url,
                startDay : startDay ,
                endDay : endDay ,
            }
            
            PostAxios('/admin/arts-center/contest',requestBody, (res)=>{
                alert("저장되었습니다.");
                window.location.reload();
            });

            }
        );
    }


    return(
        <div className="admin">
        <ul className="admin_wrapper">
        <div className="logo_box">
        <img className='logo' src={process.env.PUBLIC_URL + `.././img/present_logo.png`} 
                onClick={()=> {navigate('/admin')}} alt='logo'/>
                </div>
                <li className="deth_menu"> Member </li>
                <li className="admin_menu" onClick={()=>{navigate('/admin')}}> 회원 조회 </li>
                <hr/>
                <li className="deth_menu"> Painting </li>
                <li className="admin_menu" onClick={()=>navigate('/admin/paintings')} > 그림 조회 </li>
                <hr/>
                <li className="deth_menu"> Contest </li>
                <li className="admin_menu" onClick={()=>navigate('/admin/arts-center')} > 공모전 조회 </li>
                <li className="admin_menu" onClick={()=>window.location.reload()} > 공모전 등록 </li>
                <li className="admin_menu" onClick={()=>
                                                {sessionStorage.clear(); navigate('/'); }
                }> 로그아웃 </li>
        </ul>
        <section className="notice">
    <div className="page-title">
        <div className="container">
            <h3>공모전 등록</h3>
                <div className="contest_upload_box">
                    <input type='text' style={{"width":800, "height":20}} placeholder="공모전 제목을 입력해주세요. 예시 : [2022년 11월] 겨울"  
                    value={title} onChange={(e)=>setTitle(e.target.value)} maxLength="30"/>
    <br/>
    <br/>
                    <input type='file' onChange={saveFileImage} accept="image/*"/> 
    <br/>                
                 <p style={{"color":"red"}}> 700px*300px를 권장합니다.</p>
    <br/>
    <br/>        
                 <span>시작 일자</span> <Datetime dateFormat="YYYY-MM-DD" timeFormat="HH:mm:ss" selected={startDay}  onChange={(date)=>toggleStartDatetime(date)}/>
                 <span>종료 일자</span> <Datetime dateFormat="YYYY-MM-DD" timeFormat="HH:mm:ss" selected={endDay}   onChange={(date)=> toggleEndDatetime(date)}/>
    <br/>
    <br/>
    <br/>
    <br/>
            <MyButton type={'nothing'} text={'제출'} onClick={onDrop}/>
            </div>
    </div>
    </div>
    </section>
    </div>
    );
}

export default AdminUploadContest;